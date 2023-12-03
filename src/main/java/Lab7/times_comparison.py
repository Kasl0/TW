import pandas as pd
import matplotlib.pyplot as plt

df = pd.read_csv(r'times.csv')

grouped = df.groupby('method')

df_asym = grouped.get_group('Asym')
df_conductor = grouped.get_group('Conductor')

df_asym_mean = df_asym.groupby('no_philosophers')['time'].mean().reset_index()
df_conductor_mean = df_conductor.groupby('no_philosophers')['time'].mean().reset_index()

plt.plot(df_asym_mean['no_philosophers'], df_asym_mean['time'], label='Asym')
plt.plot(df_conductor_mean['no_philosophers'], df_conductor_mean['time'], label='Conductor')

plt.xlabel('Number of philosophers')
plt.ylabel('Avarage waiting time [ms]')
plt.title('Acquire waiting times')
plt.legend()
plt.show()
